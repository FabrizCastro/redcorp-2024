using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain
{
    public interface ISectionAndEmployeeDomain
    {
        public Task<bool> SaveAsync(SectionAndEmployee sectionAndEmployee);
        public Task<bool> UpdateAsync(int id, int Section_id, int Employee_id);
        public Task<bool> DeleteAsync(int id);
    }
}
