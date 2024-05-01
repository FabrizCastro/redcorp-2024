using AutoMapper;
using Redcorp.Services.ProfileAPI.Models;
using Redcorp.Services.ProfileAPI.Models.Dto;

namespace Redcorp.Services.ProfileAPI.Mapper
{
    public class RequestToModel : Profile
    {
        public RequestToModel()
        {
            CreateMap<EmployeeRequestDto, Employee>();
            
        }
    }
}
