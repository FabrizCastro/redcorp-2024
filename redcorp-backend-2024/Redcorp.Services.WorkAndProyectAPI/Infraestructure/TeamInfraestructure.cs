using Redcorp.Services.WorkAndProyectAPI.Context;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;
using Redcorp.Services.WorkAndProyectAPI.Models.Dto;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure
{
    public class TeamInfraestructure : ITeamInfraestructure
    {
        private WorkAndProyectDbContext _workAndProyectDbContext;

        public TeamInfraestructure(WorkAndProyectDbContext workAndProyectDbContext)
        {
            _workAndProyectDbContext = workAndProyectDbContext;
        }

        public List<Team> GetAll()
        {
            return _workAndProyectDbContext.Teams.Where(team => team.IsActive).ToList();
        }

        public Team GetById(int id)
        {
            return _workAndProyectDbContext.Teams.Find(id);
        }

        public async Task<bool> SaveAsync(Team team)
        {
            try
            {
                team.IsActive = true;
                await _workAndProyectDbContext.Teams.AddAsync(team);
                await _workAndProyectDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception exception)
            {
                throw;
                return false;
            }

        }

        public bool update(int id, Team team)
        {
            Team _team = _workAndProyectDbContext.Teams.Find(id);
            _team.Name = team.Name;
            _team.Description = team.Description;
            _team.Id_Employee = team.Id_Employee;
            _team.Id_Task = team.Id_Task;
            _team.Id_Project = team.Id_Project;

            _workAndProyectDbContext.Teams.Update(_team);

            _workAndProyectDbContext.SaveChanges();

            return true;
        }

        public bool delete(int id)
        {
            Team team = _workAndProyectDbContext.Teams.Find(id);

            team.IsActive = false;

            _workAndProyectDbContext.Teams.Update(team);

            _workAndProyectDbContext.SaveChanges();

            return true;
        }

        /*public List<Task> GetTaskByIdEmploye(int employeeId)
        {
            List<Task> tasks = new List<Task>();

            List<Team> teams = _redcorpCenterDBContext.Teams
                .Where(team => team.Id_Employee == employeeId)
                .ToList();

            foreach (Team team in teams)
            {
                Models.Task task = _redcorpCenterDBContext.Tasks.FirstOrDefault(t => t.Id == team.Id_Task);
                if (task != null)
                {
                    tasks.Add(task);
                }
            }

            return tasks;
        }*/
        public List<Team> GetTeamsById(int id)
        {
            var teams = _workAndProyectDbContext.Teams
                .Where(team => team.Id_Employee == id)
                .ToList()
                .GroupBy(team => team.Name)
                .Select(group => group.First())
                .ToList();

            return teams;
        }

        /*public List<EmployeeDto> GetEmployeesInSameProject(int employeeId)
        {
            // Obtener el área del empleado que realiza la consulta
            string employeeArea = _workAndProyectDbContext.Employees
                .Where(e => e.Id == employeeId)
                .Select(e => e.area)
                .FirstOrDefault();

            if (string.IsNullOrEmpty(employeeArea))
            {
                // El empleado no existe o no tiene un área definida
                return new List<Employee>();
            }

            // Obtener los empleados que pertenecen a la misma área
            List<Employee> employees = _redcorpCenterDBContext.Employees
                .Where(e => e.area == employeeArea && e.Id != employeeId)
                .ToList();

            return employees;
        }*/

    }
}
