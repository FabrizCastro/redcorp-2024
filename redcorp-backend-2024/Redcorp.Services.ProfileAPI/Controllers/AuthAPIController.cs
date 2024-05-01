using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Redcorp.Services.AuthAndProfileAPI.Models.Dto;
using Redcorp.Services.ProfileAPI.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.AuthAndProfileAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthAPIController : ControllerBase
    {
        private IProfileDomain _profileDomain;
        private IProfileInfraestructure _profileInfraestructure;
        private IMapper _mapper;

        public AuthAPIController(IProfileDomain profileDomain, IMapper mapper,IProfileInfraestructure profileInfraestructure)
        {
            _profileDomain = profileDomain;
            _mapper = mapper;
            _profileInfraestructure = profileInfraestructure;
        }

        [Microsoft.AspNetCore.Authorization.AllowAnonymous]
        [HttpPost]
        [Route("Login")]
        public async Task<dynamic> Login([FromBody] AuthLoginRequest authLoginRequest)
        {
            try
            {
                var user = _mapper.Map<AuthLoginRequest, Employee>(authLoginRequest);

                var jwt = await _profileDomain.Login(user);
                var user_founded = await profileDomain.GetByEmail(authLoginRequest.email);

                return new
                {
                    token = Ok(jwt),
                    user_id = user_founded.Id
                };
            }
            catch (Exception ex)
            {

                return StatusCode(StatusCodes.Status400BadRequest, "Error al procesar");
            }
        }
        [Microsoft.AspNetCore.Authorization.AllowAnonymous]
        [HttpPost]
        [Route("Signup")]
        public async Task<IActionResult> Signup([FromBody] EmployeeRequest employeesignup)
        {
            var employee = _mapper.Map<EmployeeRequest, Employee>(employeesignup);
            var id = await _employeeDomain.Signup(employee);

            if (id > 0)
                return Ok(id.ToString());
            else
                return BadRequest();
        }
    }
}
