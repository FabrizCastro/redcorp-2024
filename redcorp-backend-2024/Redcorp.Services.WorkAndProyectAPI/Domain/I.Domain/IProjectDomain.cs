using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain
{
    public interface IProjectDomain
    {
        public Task<bool> SaveAsync(Project project);
        public bool update(int id, Project project);
        public bool delete(int id);
    }
}
