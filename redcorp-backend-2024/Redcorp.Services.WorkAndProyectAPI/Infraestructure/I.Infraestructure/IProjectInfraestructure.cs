using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure
{
    public interface IProjectInfraestructure
    {
        Project GetById(int id);
        public Task<bool> SaveAsync(Project project);
        public bool update(int id, Project project);
        public bool delete(int id);
        List<Project> GetAll();
    }
}
