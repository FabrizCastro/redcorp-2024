using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models.Dto;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SectionAndEmployeeController : ControllerBase
    {
        private ISectionAndEmployeeInfraestructure _sectionAndEmployeeInfraestructure;
        private ISectionAndEmployeeDomain _sectionAndEmployeeDomain;

        public SectionAndEmployeeController(ISectionAndEmployeeInfraestructure sectionAndEmployeeInfraestructure, ISectionAndEmployeeDomain sectionAndEmployeeDomain)
        {
            _sectionAndEmployeeInfraestructure = sectionAndEmployeeInfraestructure;
            _sectionAndEmployeeDomain = sectionAndEmployeeDomain;
        }


        // GET: api/<SectionAndEmployeeController>
        [HttpGet]
        public async Task<List<SectionAndEmployee>> GetAsync()
        {
            try
            {
                return await _sectionAndEmployeeInfraestructure.GetAllAsync();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }

        }

        // GET api/<SectionAndEmployeeController>/5
        [HttpGet("{id}", Name = "SectionsEmployees")]
        public async Task<SectionAndEmployee> GetByIdAsync(int id)
        {
            try
            {
                SectionAndEmployee sectionsAndEmployees = await _sectionAndEmployeeInfraestructure.GetByIdAsync(id);
                return sectionsAndEmployees;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }


        [HttpGet("GetEmployees/{sectionid}", Name = "GetEmployees")]
        public async Task<List<EmployeeDto>> GetEmployeesBySectionIdByAsync(int sectionid)
        {
            try
            {
                return await _sectionAndEmployeeInfraestructure.GetEmployeesBySectionId(sectionid);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }


        [HttpGet("GetSections/{employeeid}", Name = "GetSections")]
        public async Task<List<Section>> GetSectionsByEmployeeId(int employeeid)
        {
            try
            {
                return await _sectionAndEmployeeInfraestructure.GetSectionsByEmployeeId(employeeid);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }


        // POST api/<SectionAndEmployeeController>
        [HttpPost]
        public async Task<IActionResult> PostAsync([FromBody] SectionAndEmployeeRequest sectionsAndEmployeesRequest)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    SectionAndEmployee sectionAndEmployee = new SectionAndEmployee()
                    {
                        Section_Id = sectionsAndEmployeesRequest.Section_Id,
                        Employees_Id = sectionsAndEmployeesRequest.Employees_Id
                    };
                    await _sectionAndEmployeeDomain.SaveAsync(sectionAndEmployee);
                    return Ok();
                }
                else
                {
                    return StatusCode(400);
                }
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }
        }

        // PUT api/<SectionAndEmployeeController>/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAsync(int id, [FromBody] SectionAndEmployeeRequest sectionAndEmployeeRequest)
        {
            try
            {
                await _sectionAndEmployeeDomain.UpdateAsync(id, sectionAndEmployeeRequest.Section_Id, sectionAndEmployeeRequest.Employees_Id);
                return Ok();
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }
        }

        // DELETE api/<SectionAndEmployeeController>/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAsync(int id)
        {
            try
            {
                await _sectionAndEmployeeDomain.DeleteAsync(id);
                return Ok();
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }

        }
    }
}
