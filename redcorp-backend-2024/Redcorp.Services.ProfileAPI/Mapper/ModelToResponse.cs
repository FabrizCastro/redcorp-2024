using AutoMapper;
using Redcorp.Services.ProfileAPI.Models;
using Redcorp.Services.ProfileAPI.Models.Dto;

namespace Redcorp.Services.ProfileAPI.Mapper
{
    public class ModelToResponse : Profile
    {
        public ModelToResponse()
        {
            CreateMap<Employee, EmployeeResponseDto>();
        }
    }
}
