using Redcorp.Services.AuthAndProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;

namespace Redcorp.Services.AuthAndProfileAPI.Middleware
{
    public class JwtMiddleware
    {
        private readonly RequestDelegate _next;

        public JwtMiddleware(RequestDelegate next)
        {
            _next = next;
        }


        /// <summary>
        /// Autenticación
        /// </summary>
        /// <param name="context"></param>
        /// <param name="tokenDomain"></param>
        /// <param name="profileDomain"></param>
        public async Task Invoke(HttpContext context, ITokenDomain tokenDomain, IProfileDomain profileDomain)
        {
            //Autenticación

            var token = context.Request.Headers["Authorization"].FirstOrDefault()?.Split(" ").Last();
            var email = tokenDomain.ValidateJwt(token);

            if (email != null)
            {
                context.Items["User"] = await profileDomain.GetByEmailAsync(email);
            }
                
            await _next(context);
        }
    }
}
