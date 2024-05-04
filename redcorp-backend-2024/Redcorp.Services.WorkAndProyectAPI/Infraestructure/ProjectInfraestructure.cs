using Redcorp.Services.WorkAndProyectAPI.Context;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure
{
    public class ProjectInfraestructure : IProjectInfraestructure
    {
        private WorkAndProyectDbContext _context;

        public ProjectInfraestructure(WorkAndProyectDbContext context)
        {
            _context = context;
        }

        public bool delete(int id)
        {
            Project project = _context.Projects.Find(id);

            project.IsActive = false;

            _context.Projects.Update(project);

            _context.SaveChanges();

            return true;
        }

        public List<Project> GetAll()
        {
            return _context.Projects.Where(project => project.IsActive).ToList();
        }

        public Project GetById(int id)
        {
            return _context.Projects.Find(id);
        }

        public async Task<bool> SaveAsync(Project project)
        {
            try
            {
                project.IsActive = true;
                await _context.Projects.AddAsync(project);
                await _context.SaveChangesAsync();
                return true;
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }

        public bool update(int id, Project project)
        {
            Project _project = _context.Projects.Find(id);
            _project.Name = project.Name;
            _project.Description = project.Description;
            _project.FinalDate = project.FinalDate;
            _project.State = project.State;

            _context.Projects.Update(_project);

            _context.SaveChanges();

            return true;
        }
    }
}
