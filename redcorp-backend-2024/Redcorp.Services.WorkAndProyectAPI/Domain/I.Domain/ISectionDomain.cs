using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain
{
    public interface ISectionDomain
    {
        public bool Save(Section section);
        public bool update(int id, string section_name, string description);
        public bool delete(int id);
        public Task<bool> SaveAsync(Section section);
        public Task<bool> UpdateAsync(int id, string section_name, string description);
        public Task<bool> DeleteAsync(int id);
    }
}
