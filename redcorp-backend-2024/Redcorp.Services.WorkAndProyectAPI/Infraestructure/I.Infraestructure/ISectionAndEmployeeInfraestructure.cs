using Redcorp.Services.WorkAndProyectAPI.Models;
using Redcorp.Services.WorkAndProyectAPI.Models.Dto;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure
{
    public interface ISectionAndEmployeeInfraestructure
    {
        Task<SectionAndEmployee> GetByIdAsync(int id);
        public Task<bool> SaveAsync(SectionAndEmployee sectionAndEmployees);
        public Task<bool> UpdateAsync(int id, int Section_Id, int Employee_Id);
        public Task<bool> DeleteAsync(int id);

        public Task<bool> existsSectionIdAndEmployeeId(SectionAndEmployee sectionAndEmployee);
        public Task<List<EmployeeDto>> GetEmployeesBySectionId(int sectionId);
        public Task<List<Section>> GetSectionsByEmployeeId(int employeeId);
        Task<List<SectionAndEmployee>> GetAllAsync();
    }
}
