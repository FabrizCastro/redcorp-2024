using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure
{
    public interface IProfileInfraestructure
    {
        Task<Employee> GetByIdAsync(int id);
        Task<bool> SaveAsync(Employee employee);
        Task<bool> UpdateAsync(int id,Employee employee);
        Task<bool> DeleteAsync(int id);

        Task<Employee> GetByEmailAsync(string email);
        Task<int> SignupAsync(Employee employee);
        Task<Employee> GetByLoginAsync(string email, string password);
        Task<List<Employee>> GetAllAsync();
    }
}
