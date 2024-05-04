using Redcorp.Services.WorkAndProyectAPI.Models;
using Redcorp.Services.WorkAndProyectAPI.Models.Dto;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure
{
    public interface ITeamInfraestructure
    {
        Team GetById(int id);
        public Task<bool> SaveAsync(Team team);
        public bool update(int id, Team team);
        public bool delete(int id);
        List<Team> GetAll();
        //List<Task> GetTaskByIdEmploye(int id);
        public List<Team> GetTeamsById(int id);
        //public List<EmployeeDto> GetEmployeesInSameProject(int employeeId);
    }
}
