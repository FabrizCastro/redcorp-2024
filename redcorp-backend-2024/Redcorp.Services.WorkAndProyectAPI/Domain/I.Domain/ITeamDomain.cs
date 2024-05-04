using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain
{
    public interface ITeamDomain
    {
        public Task<bool> SaveAsync(Team task);
        public bool update(int id, Team task);
        public bool delete(int id);
    }
}
