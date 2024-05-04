using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Domain.I.Domain
{
    public interface IProfileDomain
    {
        Task<bool> SaveAsync(Employee employee);
        Task<bool> UpdateAsync(int id,Employee employee);
        Task<bool> DeleteAsync(int id);
        Task<int> SignupAsync(Employee employee);
        Task<string> LoginAsync(Employee employee);
        Task<Employee> GetByEmailAsync(string username);
    }
}
