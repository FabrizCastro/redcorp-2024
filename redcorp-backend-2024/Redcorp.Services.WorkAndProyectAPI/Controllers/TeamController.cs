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
    public class TeamController : ControllerBase
    {
        private ITeamInfraestructure _teamInfraestructure;
        private ITeamDomain _teamDomain;

        public TeamController(ITeamInfraestructure teamInfraestructure, ITeamDomain teamDomain)
        {
            _teamInfraestructure = teamInfraestructure;
            _teamDomain = teamDomain;
        }


        [HttpGet]
        public List<Team> Get()
        {
            return _teamInfraestructure.GetAll();
        }


        [HttpGet("{TeamId}")]
        public TeamResponse GetTeamById(int id)
        {
            Team team = _teamInfraestructure.GetById(id);

            TeamResponse teamResponse = new TeamResponse()
            {
                Id = team.Id,
                Name = team.Name,
                Description = team.Description,
                Id_Employee = team.Id_Employee,
                Id_Project = team.Id_Project,
                Id_Task = team.Id_Task,
            };

            return teamResponse;
        }

        [HttpPost]
        public async Task<IActionResult> Post([FromBody] TeamRequest value)
        {
            if (ModelState.IsValid)
            {
                Team team = new Team()
                {
                    Name = value.Name,
                    Description = value.Description,
                    Id_Employee = value.Id_Employee,
                    Id_Project = value.Id_Project,
                    Id_Task = value.Id_Task,
                };

                var result = await _teamDomain.SaveAsync(team);

                return result ? StatusCode(201) : StatusCode(500);
            }
            else
            {
                return StatusCode(400);


            }

        }

        [HttpPut("{id}")]
        public void Put(int id, [FromBody] TeamRequest value)
        {
            if (ModelState.IsValid)
            {
                Team team = new Team()
                {
                    Name = value.Name,
                    Description = value.Description,
                    Id_Employee = value.Id_Employee,
                    Id_Project = value.Id_Project,
                    Id_Task = value.Id_Task,
                };
                _teamDomain.update(id, team);
            }
            else
            {
                StatusCode(400);
            }
        }

        /*[HttpGet("GetTasksByIdEmployee/{id_employee}", Name = "GetTasksByIdEmployee")]
        public List<Task> GetTasksByIdEmployee(int id_employee)
        {
            return _teamInfraestructure.GetTaskByIdEmploye(id_employee);
        }*/

        [HttpGet("GetTeamsById/{id_employee}", Name = "GetTeamsById")]
        public List<Team> GetTeamsByIdEmployee(int id_employee)
        {
            return _teamInfraestructure.GetTeamsById(id_employee);
        }

        /*[HttpGet("GetEmployeesContactsByTeamId/{id_employee}", Name = "GetEmployeesContactsByTeamId")]
        public List<Employee> GetEmployeesByTeamId(int id_employee)
        {
            return _teamInfraestructure.GetEmployeesInSameProject(id_employee);
        }*/



        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            _teamDomain.delete(id);
        }
    }
}
