package com.Redcorpmicroservice.workandproyect.controller;

import com.Redcorpmicroservice.workandproyect.model.Team;
import com.Redcorpmicroservice.workandproyect.model.dto.TeamRequest;
import com.Redcorpmicroservice.workandproyect.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/redcorp/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody TeamRequest teamRequest) {
        Team team = new Team();
        team.setTeamName(teamRequest.getTeamName());
        team.setTeamDescription(teamRequest.getTeamDescription());

        teamService.save(team);
    }

    @GetMapping
    public ResponseEntity<?> findAllTeams(){
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> findById(@PathVariable(name = "teamId") Long teamId)
    {
        return ResponseEntity.ok(teamService.findById(teamId));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable(name = "teamId") Long teamId,@RequestBody TeamRequest teamRequest){

        Team team = teamService.findById(teamId);

        if(team == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        team.setTeamName(teamRequest.getTeamName());
        team.setTeamDescription(teamRequest.getTeamDescription());

        return new ResponseEntity<Team>(teamService.updateTeam(team), HttpStatus.OK);
    }


    @GetMapping("/search-employee/{teamId}")
    public ResponseEntity<?> findEmployeesByTeamId(@PathVariable Long teamId){
        return ResponseEntity.ok(teamService.findEmployeesByTeamId(teamId));
    }

    @GetMapping("/search-project/{teamId}")
    public ResponseEntity<?> findProjectsByTeamId(@PathVariable Long teamId){
        return ResponseEntity.ok(teamService.findProjectsByTeamId(teamId));
    }

}
