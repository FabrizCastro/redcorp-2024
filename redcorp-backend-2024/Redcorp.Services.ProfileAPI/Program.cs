using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Redcorp.Services.AuthAndProfileAPI.Domain.I.Domain;
using Redcorp.Services.AuthAndProfileAPI.Domain;
using Redcorp.Services.ProfileAPI.Context;
using Redcorp.Services.ProfileAPI.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Mapper;
using Redcorp.Services.AuthAndProfileAPI.Middleware;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

//dependecy inyection
builder.Services.AddScoped<IProfileInfraestructure, ProfileInfraestructure>();
builder.Services.AddScoped<IProfileDomain, ProfileDomain>();


builder.Services.AddScoped<IEncryptDomain, EncryptDomain>();
builder.Services.AddScoped<ITokenDomain, TokenDomain>();
//Conexion a MYSQL
//-----------------
var connectionString = builder.Configuration.GetConnectionString("redcorpProfileServicesConnection");
var serverVersion = new MySqlServerVersion(new Version(8, 0, 29));

builder.Services.AddAuthentication(options =>
{
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
});

builder.Services.AddDbContext<ProfileDbContext>(
    dbContextOptions =>
    {
        dbContextOptions.UseMySql(connectionString,
            ServerVersion.AutoDetect(connectionString),
            options => options.EnableRetryOnFailure(
                maxRetryCount: 5,
                maxRetryDelay: System.TimeSpan.FromSeconds(30),
                errorNumbersToAdd: null)
        );
    });
//-----------------
builder.Services.AddAutoMapper(typeof(ModelToResponse), typeof(RequestToModel));    

builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAllOrigins", builder =>
        builder.AllowAnyOrigin()
            .AllowAnyHeader()
            .AllowAnyMethod());
});

var app = builder.Build();

app.UseCors("AllowAllOrigins");

using (var scope = app.Services.CreateScope())
using (var context = scope.ServiceProvider.GetService<ProfileDbContext>())
{
    context.Database.EnsureCreated();
}

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
app.UseMiddleware<JwtMiddleware>();

app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
