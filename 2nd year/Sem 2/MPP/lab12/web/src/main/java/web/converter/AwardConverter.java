//package web.converter;
//
//import core.domain.Award;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import web.dto.AwardDTO;
//import web.dto.TeamDTO;
//
//@Component
//public class AwardConverter extends BaseConverter<Long, Award, AwardDTO> {
//    public static final Logger logger = LoggerFactory.getLogger(AwardConverter.class);
//
//    @Override
//    public Award convertDtoToModel(AwardDTO dto) {
//        var model = new Award();
//        model.setId(dto.getId());
//        model.setDate(dto.getDate());
//        return model;
//    }
//
//    @Override
//    public AwardDTO convertModelToDto(Award award) {
//        var awardDto = new AwardDTO(award.getDate());
//        awardDto.setId(award.getId());
//        return awardDto;
//    }
//}
