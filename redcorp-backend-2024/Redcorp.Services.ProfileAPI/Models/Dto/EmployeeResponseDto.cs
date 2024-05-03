namespace Redcorp.Services.ProfileAPI.Models.Dto
{
    public class EmployeeResponseDto
    {
        public int id { get; set; }
        public string? name { get; set; }
        public string? last_name { get; set; }

        public string? dni { get; set; }
        public string? email { get; set; }
        public string? area { get; set; }
        public string? cargo { get; set; }
        public string? photo { get; set; }
        public string? rol { get; set; }
    }
}
