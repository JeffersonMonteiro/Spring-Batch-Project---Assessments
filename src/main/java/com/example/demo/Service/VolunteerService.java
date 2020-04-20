package com.example.demo.Service;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Exception.APIException;
import com.example.demo.Repository.ActivityRepository;
import com.example.demo.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

import static com.example.demo.Exception.APIExceptionCode.VERR001;
import static com.example.demo.Exception.APIExceptionCode.VERR002;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private ActivityRepository activityRepository;

    private static Validator validator;

    // read all
    public Iterable<Volunteer> getAll(){
        return volunteerRepository.findAll();
    }

    //create
    public Volunteer createVolunteer(Volunteer volunteer) throws  Exception{

        if(volunteer.getName() == null) {
            throw new APIException(VERR001);
        }else if (volunteer.getAge() < 18) {
            throw  new APIException(VERR002);
        } else{
            return volunteerRepository.save(volunteer);
        }

    }

    //read one
    public Volunteer findById(int id){
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        return optionalVolunteer.orElseThrow(() -> new RuntimeException("Volunteer does not exist on database"));
    }
    //update
    public  Volunteer updateVolunteer(Volunteer volunteer, Integer id){
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);

        if (optionalVolunteer.isPresent()){
                return volunteerRepository.save(volunteer);
        }
//        return optionalVolunteer.orElseThrow(() -> new RuntimeException("Volunteer id not found"));
        return volunteer;
    }

    //update activityList
    public void updateActivityList(Volunteer volunteer, Activity activity){
        volunteer.getActivityList().add(activity);
    }


    //delete
    public void deleteVolunteer(int id){
        volunteerRepository.deleteById(id);
    }

    public  void deleteActivityFromVolunteer(Activity activity, int volunteerId){
        Volunteer volunteer = findById(volunteerId);
        volunteer.getActivityList().remove(activity);
        updateVolunteer(volunteer, volunteerId);
    }

    //constructor

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }
}
