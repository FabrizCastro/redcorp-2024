using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;
using Redcorp.Services.ProfileAPI.Models.Dto;
using Redcorp.Services.AuthAndProfileAPI.Filter;
using Microsoft.AspNetCore.Authorization;
using Redcorp.Services.AuthAndProfileAPI.Models.Dto;

namespace Redcorp.Services.ProfileAPI.Controllers
{
    [Route("api/v1/[controller]")]
    [ApiController]
    public class ProfileAPIController : ControllerBase
    {
        private IProfileDomain _profileDomain;
        private IProfileInfraestructure _profileInfraestructure;
        private IMapper _mapper;
        public IConfiguration _configuration;
        public ProfileAPIController(IProfileDomain profileDomain, IProfileInfraestructure profileInfraestructure, IMapper mapper, IConfiguration configuration)
        {
            _profileDomain = profileDomain;
            _profileInfraestructure = profileInfraestructure;
            _mapper = mapper;
            _configuration = configuration;
        }

        [AutorizationAtribute("user,admin")]
        [HttpGet]
        public async Task<List<EmployeeResponseDto>> GetAsync()
        {

            var employees = await _profileInfraestructure.GetAllAsync();

            return _mapper.Map<List<Employee>, List<EmployeeResponseDto>>(employees);
        }

        [AutorizationAtribute("user,admin")]
        [HttpGet("{id}", Name = "Get")]
        public async Task<EmployeeResponseDto> Get(int id)
        {
            Employee employee = await _profileInfraestructure.GetByIdAsync(id);

            var employeeResponse = _mapper.Map<Employee, EmployeeResponseDto>(employee);

            return employeeResponse;

        }

        [AutorizationAtribute("admin")]
        [HttpPost]
        public async Task<IActionResult> PostAsync([FromBody] EmployeeRequestDto employeeRequest)
        {
            if (ModelState.IsValid)
            {


                var employee = _mapper.Map<EmployeeRequestDto, Employee>(employeeRequest);

                var result = await _profileDomain.SaveAsync(employee);

                return result ? StatusCode(201) : StatusCode(500);
            }
            else
            {
                return StatusCode(400);
            }
        }
        [AutorizationAtribute("user,admin")]
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] EmployeeRequestPutDto employeeRequest)
        {
            if (ModelState.IsValid)
            {
                await _profileDomain.UpdateAsync(id,_mapper.Map<EmployeeRequestPutDto, Employee>(employeeRequest));
                return Ok(); 
            }
            else
            {
                return BadRequest(); 
            }
        }

        [AutorizationAtribute("admin")]
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            bool deleted = await _profileDomain.DeleteAsync(id);
            if (deleted)
            {
                return NoContent(); 
            }
            else
            {
                return NotFound(); 
            }
        }
    }
}
