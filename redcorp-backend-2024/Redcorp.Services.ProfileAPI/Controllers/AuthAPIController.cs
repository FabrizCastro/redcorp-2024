using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Redcorp.Services.AuthAndProfileAPI.Filter;
using Redcorp.Services.AuthAndProfileAPI.Models;
using Redcorp.Services.AuthAndProfileAPI.Models.Dto;
using Redcorp.Services.ProfileAPI.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;
using Redcorp.Services.ProfileAPI.Models.Dto;

namespace Redcorp.Services.AuthAndProfileAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthAPIController : ControllerBase
    {
        private IProfileDomain _profileDomain;
        private IProfileInfraestructure _profileInfraestructure;
        private IMapper _mapper;
        public IConfiguration _configuration;

        public AuthAPIController(IProfileDomain profileDomain, IMapper mapper,IProfileInfraestructure profileInfraestructure, IConfiguration configuration)
        {
            _profileDomain = profileDomain;
            _mapper = mapper;
            _profileInfraestructure = profileInfraestructure;
            _configuration = configuration; 
        }

        [AllowAnonymousAttribute]
        [HttpPost]
        [Route("Login")]
        public async Task<dynamic> Login([FromBody] AuthLoginRequest authLoginRequest)
        {
            try
            {
                var user = _mapper.Map<AuthLoginRequest, Employee>(authLoginRequest);

                var jwt = await _profileDomain.LoginAsync(user);
                var user_founded = await _profileDomain.GetByEmailAsync(authLoginRequest.email);

                return new
                {
                    token = Ok(jwt),
                    user_id = user_founded.id
                };
            }
            catch (Exception ex)
            {

                return StatusCode(StatusCodes.Status400BadRequest, "Error al procesar");
            }
        }
        [AllowAnonymousAttribute]
        [HttpPost]
        [Route("Signup")]
        public async Task<dynamic> Signup([FromBody] EmployeeRequestDto employeesignup)
        {
            try
            {
                var employee = _mapper.Map<EmployeeRequestDto, Employee>(employeesignup);
                var id = await _profileDomain.SignupAsync(employee);

                if (id > 0)
                {
                    var employeeSignup = _mapper.Map<EmployeeRequestDto, Employee>(employeesignup);
                    var jwt = await _profileDomain.LoginAsync(employeeSignup);
                    var user_founded = await _profileDomain.GetByEmailAsync(employeeSignup.email);

                    return new
                    {
                        token = Ok(jwt),
                        user_id = user_founded.id
                    };
                }
                else
                    return BadRequest();

            }
            catch(Exception ex)
            {
                return StatusCode(StatusCodes.Status400BadRequest, ex);
            }
        }
    }
}
