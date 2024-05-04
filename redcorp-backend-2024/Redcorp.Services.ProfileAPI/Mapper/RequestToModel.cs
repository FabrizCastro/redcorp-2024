using AutoMapper;
using Redcorp.Services.AuthAndProfileAPI.Models.Dto;
using Redcorp.Services.ProfileAPI.Models;
using Redcorp.Services.ProfileAPI.Models.Dto;

namespace Redcorp.Services.ProfileAPI.Mapper
{
    public class RequestToModel : Profile
    {
        public RequestToModel()
        {
            CreateMap<EmployeeRequestDto, Employee>();
            CreateMap<AuthLoginRequest, Employee>();
            CreateMap<EmployeeRequestPutDto,Employee>();
        }
    }
}
