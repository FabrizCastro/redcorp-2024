using Microsoft.EntityFrameworkCore;
using Redcorp.Services.ProfileAPI.Context;
using Redcorp.Services.ProfileAPI.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Mapper;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

//dependecy inyection
builder.Services.AddScoped<IProfileInfraestructure, ProfileInfraestructure>();
builder.Services.AddScoped<IProfileDomain, ProfileDomain>();
builder.Services.AddAutoMapper(typeof(ModelToResponse), typeof(RequestToModel));
//Conexion a MYSQL
//-----------------
var connectionString = builder.Configuration.GetConnectionString("redcorpProfileServicesConnection");

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

var app = builder.Build();

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

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
