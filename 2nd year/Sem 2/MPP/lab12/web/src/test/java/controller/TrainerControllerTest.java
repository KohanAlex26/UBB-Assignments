package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.domain.Award;
import core.domain.Sportive;
import core.domain.Team;
import core.domain.Trainer;
import core.service.TrainerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.controller.TrainerController;
//import web.converter.AwardConverter;
import web.converter.TeamConverter;
import web.converter.TrainerConverter;
import web.dto.AwardDTO;
import web.dto.SportiveDTO;
import web.dto.TrainerDTO;

import java.util.*;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrainerControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TrainerController trainerController;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainerConverter trainerConverter;

    private Trainer trainer1;
    private Trainer trainer2;
    private TrainerDTO trainerDTO1;
    private TrainerDTO trainerDTO2;
    private Award award1;
    private Award award2;
    private AwardDTO awardDTO1;
    private AwardDTO awardDTO2;

//    @Mock
//    private AwardConverter awardConverter;

    @Mock
    private TeamConverter teamConverter;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(trainerController)
                .build();
        initData();
    }

    @Test
    public void getTrainersFromRepository() throws Exception {
        List<Trainer> trainers = Arrays.asList(trainer1, trainer2);
        List<TrainerDTO> trainersDTO =
                new ArrayList<>(Arrays.asList(trainerDTO1, trainerDTO2));
        when(trainerService.getTrainersFromRepository()).thenReturn(trainers);
        when(trainerConverter.convertModelsToDTOs(trainers)).thenReturn(trainersDTO);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/trainers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.trainers", hasSize(2)))
                .andExpect(jsonPath("$.trainers[0].firstName", anyOf(is("c1"), is("c2"))))
                .andExpect(jsonPath("$.trainers[1].lastName", anyOf(is("b1"), is("b2"))));
        resultActions.andReturn().getResponse().getContentAsString();

        verify(trainerService, times(1)).getTrainersFromRepository();
        verify(trainerConverter, times(1)).convertModelsToDTOs(trainers);
        verifyNoMoreInteractions(trainerService, trainerConverter);
    }


    @Test
    public void updateTrainer() throws Exception {
        when(trainerConverter.convertDtoToModel(trainerDTO1)).thenReturn(trainer1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/trainers/" + trainer1.getId(), trainerDTO1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(trainerDTO1)))
                .andExpect(status().isOk());

//        verify(trainerService, times(1)).updateTrainer(trainer1.getId(),
//                trainerDTO1.getFirstName(), trainerDTO1.getLastName(), trainerDTO1.getAge(), trainerDTO1.getAward());
        verify(trainerConverter, times(1)).convertDtoToModel(trainerDTO1);
        verifyNoMoreInteractions(trainerService, trainerConverter);
    }

//    @Test
//    public void addTrainer() throws Exception {
////        System.out.println(trainerDTO1);
//        when(trainerConverter.convertDtoToModel(trainerDTO1)).thenReturn(trainer1);
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/trainers", trainerDTO1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJsonString(trainerDTO1)))
//                .andExpect(status().isOk());
//
//        verify(trainerService, times(1)).addTrainer(trainerDTO1.getFirstName(), trainerDTO1.getLastName(), trainerDTO1.getAge(), awardConverter.convertDtoToModel(trainerDTO1.getAward()));
//        verify(trainerConverter, times(1)).convertDtoToModel(trainerDTO1);
//        verifyNoMoreInteractions(trainerService, trainerConverter);
//    }

    @Test
    public void deleteTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/trainers/" + trainer1.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(trainerService, times(1)).deleteTrainer(trainer1.getId());
        verifyNoMoreInteractions(trainerService, trainerConverter);
    }

    private String toJsonString(TrainerDTO trainerDTO) {
        try {
            return new ObjectMapper().writeValueAsString(trainerDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void initData() {
//        Sportive sportive1=new Sportive(1L,"a","a",1,new Team(1L,"a"));
//        System.out.println(sportive1);
//        SportiveDTO sportiveDTO=new SportiveDTO(teamConverter.convertModelToDto(sportive1.getTeam()),sportive1.getFirstName(),sportive1.getLastName(),sportive1.getAge());
//        System.out.println(sportiveDTO);
        award1=new Award("a");
        awardDTO1=new AwardDTO(award1.getDate());
//        awardDTO1.setId(award1.getId());

        award2=new Award("b");
        awardDTO2=new AwardDTO(award2.getDate());
//        awardDTO2.setId(award2.getId());

        trainer1 = new Trainer(1L, "c1", "b1", 2, award1);
        trainer2 = new Trainer(2L, "c2", "b2", 3, award2);

        trainerDTO1 = new TrainerDTO(awardDTO1,trainer1.getFirstName(), trainer1.getLastName(), trainer1.getAge());
        trainerDTO1.setId(trainer1.getId());
        trainerDTO2 = new TrainerDTO(awardDTO2,trainer2.getFirstName(), trainer2.getLastName(), trainer2.getAge());
        trainerDTO2.setId(trainer2.getId());
    }
}
