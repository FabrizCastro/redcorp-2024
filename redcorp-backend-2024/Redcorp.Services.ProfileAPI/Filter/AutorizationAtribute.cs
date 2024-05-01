using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.AuthAndProfileAPI.Filter
{
    public class AutorizationAtribute : Attribute, IAuthorizationFilter
    {
        private readonly List<string> _roles;

        public AutorizationAtribute(params string[] roles)
        {
            _roles = (roles.Count() > 0) ? roles.FirstOrDefault().Split(",").ToList() : new List<string>();
        }
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            // If action is decorated with [AllowAnonymous] attribute
            var allowAnonymous = context.ActionDescriptor.EndpointMetadata.OfType<AllowAnonymousAttribute>().Any();
            // Then skip authorization process
            if (allowAnonymous)
                return;


            // Authorization process
            var user = (Employee)context.HttpContext.Items["User"];

            if (user == null || !_roles.Any() || (_roles.Any() && !_roles.Contains(user.rol)))
            {
                context.Result = new JsonResult(new { message = "Unathorized" }) { StatusCode = StatusCodes.Status401Unauthorized };
            }
        }
    }
}
