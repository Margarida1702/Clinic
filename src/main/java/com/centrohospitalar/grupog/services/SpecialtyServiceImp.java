package com.centrohospitalar.grupog.services;



import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.exceptions.SpecialtyException;
import com.centrohospitalar.grupog.repositories.DoctorRepository;
import com.centrohospitalar.grupog.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialtyServiceImp implements SpecialtyService {

    @Autowired
    SpecialtyRepository specialtyRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Specialty> getSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties;
    }

    public Map<Specialty,Integer> SpecialtiesDoctorCount(){
        List <Specialty> specialties = specialtyRepository.findAll();
        Map<Specialty,Integer> specialtyNumberOfDoctors= new HashMap<Specialty,Integer>();


        for(Specialty sp: specialties){
            Integer count = doctorRepository.countDoctorsBySpecialtyListContaining(sp);
            specialtyNumberOfDoctors.put(sp,count);
        }

        return specialtyNumberOfDoctors;
    }

    @Override
    public boolean addSpecialtyToDoctor(Specialty specialty, Doctor doctor) {
        if(doctor.getSpecialtyList().contains(specialty)){
            return false;
        }else {
            doctor.getSpecialtyList().add(specialty);
        }
        return true;
    }

    @Override
    public void initSpecialties() {
        List<Specialty> specialtyList = new ArrayList<>();

        specialtyList.add(new Specialty("Cardiologia", "Cardiologia é a especialidade médica que lida com distúrbios do coração e do sistema cardiovascular. O campo inclui diagnóstico médico e tratamento de defeitos cardíacos congênitos, doença arterial coronariana, insuficiência cardíaca, doença cardíaca valvular e eletrofisiologia.", "cardiology"));
        specialtyList.add(new Specialty("Dermatologia ", "Dermatologia é a especialidade médica que se ocupa do diagnóstico e tratamento clínico-cirúrgico das enfermidades relacionados à pele e aos anexos cutâneos (unhas, pelos, mucosas, cabelos). Dentro da dermatologia, existe a dermatovenerologia, especialidade que tem importante atuação no contexto das infeções sexualmente transmissíveis.", "dermatology"));
        specialtyList.add(new Specialty("Ginecologia", "A ginecologia é a prática da medicina que lida diretamente com a saúde do aparelho reprodutor feminino (vagina, útero e ovários) e seios .O seu significado literal é \"a ciência da mulher\".", "gynecology"));
        specialtyList.add(new Specialty("Imunologia", "Imunologia é a especialidade médica que estuda o sistema imunitário e lida com o funcionamento fisiológico assim como doenças imunológicas e doenças autoimunes como hipersensibilidade, deficiência imune, rejeição pós enxerto, características físicas, químicas e fisiológicas dos componentes do sistema imune in vitro, in situ e in vivo. O ramo da imunologia que estuda a sua interação com o comportamento e o sistema neuro-endócrino chama-se psiconeuroimunologia.", "immunology"));
        specialtyList.add(new Specialty("Neurologia", "Neurologia é a especialidade médica que lida com distúrbios do sistema nervoso. A neurologia lida com o diagnóstico e tratamento de todas as categorias de condições e doenças que envolvem o cérebro, a medula espinhal e os nervos periféricos. A neurologia trata de uma infinidade de condições neurológicas, incluindo acidentes vasculares cerebrais, convulsões, distúrbios do movimento, distúrbios neurológicos autoimunes, como esclerose múltipla, distúrbios de dor de cabeça, como enxaqueca e demências.", "neurology"));
        specialtyList.add(new Specialty("Oncologia", "A oncologia ou cancrologia, é a especialidade médica trata e estuda os cancros, que são tumores malignos. Existem tratamentos específicos para cada tipo de cancro; os métodos de tratamentos mais utilizados são a cirurgia, a radioterapia, a quimioterapia e a hormonioterapia. O tratamento multidisciplinar é de suma importância e tal tratamento envolve oncologistas, cirurgiões, radioterapeutas, patologistas, radiologistas, farmacêuticos, enfermeiros, psicólogos, nutricionistas, fisioterapeutas, dentistas entre outros profissionais, devido à enorme complexidade da doença e às suas diferentes abordagens terapêuticas.", "oncology"));
        specialtyList.add(new Specialty("Pediatria", "A pediatria é a especialidade médica dedicada à assistência à criança e ao adolescente, nos seus diversos aspectos, sejam eles preventivos ou curativos. Os aspectos preventivos envolvem ações como o aleitamento materno, imunizações (vacinas), prevenção de acidentes, além do acompanhamento e das orientações necessárias a um crescimento e desenvolvimento saudáveis (puericultura). Já os curativos correspondem aos diversos procedimentos e tratamentos das mais diversas doenças exclusivas ou não da criança e adolescente.", "pediatrics"));
        specialtyList.add(new Specialty("Radiologia", "A radiologia a especialidade da medicina, da odontologia, da indústria, forense entre outras áreas que utiliza as radiações para a realização de diagnósticos, controle e tratamento de doenças. Ela permite a visualização de ossos, órgãos ou estruturas através do uso de radiações (sonoras, eletromagnéticas ou corpusculares), gerando desta maneira uma imagem. Nas últimas décadas foram acrescentados novos métodos de imagem como a tomografia computadorizada, a mamografia, a ultrassonografia e a ressonância magnética nuclear.", "radiology"));
        specialtyList.add(new Specialty("Cirurgia", "Cirurgia é a especialidade que trata das questões relativas a \"procedimentos cirúrgicos\" seja qualquer tipo de procedimento no qual o cirurgião e sua equipe realizam uma intervenção manual ou instrumental no corpo do paciente para diagnosticar, tratar ou curar doenças ou traumatismos ou para melhorar a funcionalidade ou aparência de parte do corpo.", "surgery"));

        for (Specialty sp: specialtyList){
            specialtyRepository.save(sp);
        }

    }

    @Override
    public Specialty getSpecialtyByName(String name) {
        return specialtyRepository.getSpecialtyByName(name);
    }


    @Override
    public boolean existsByName(String name){
        for (Specialty specialty: getSpecialties()){
            if (specialty.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Specialty createSpecialty(String name, String description, String imageName) throws SpecialtyException {
        Specialty specialty = new Specialty();

        if (name=="")
            throw new SpecialtyException("Campo nome não preenchido");
        if (description=="")
            throw new SpecialtyException("Campo descrição não preenchida");
        if(description.length()>1500)
            throw new SpecialtyException("Atingido limite de 1500 caracteres na descrição :(");
        if(existsByName(name))
            throw new SpecialtyException("Especialidade já existe!");
        if (imageName=="")
            throw new SpecialtyException("Campo nome da imagem não preenchido");

        specialty.setName(name);
        specialty.setDescription(description);
        specialty.setImageName(imageName);

        specialtyRepository.save(specialty);

        return specialty;
    }
    @Override
    public Specialty updateSpecialty(Long id, String name, String description, String imageName) throws SpecialtyException {
        Specialty specialty = specialtyRepository.getSpecialtyById(id);

        if(name.equals(""))
            throw new SpecialtyException("Nome não deve estar vazio!");
        if(description.equals(""))
            throw new SpecialtyException("Descrição não deve estar vazia");
        if(description.length()>1500)
            throw new SpecialtyException("Descrição com mais de 1500 caracteres");
        if(imageName.equals(""))
            throw new SpecialtyException("Nome da imagem não deve estar vazio");
        if(imageName.endsWith(".png"))
            throw new SpecialtyException("O nome da imagem não deve conter a extensão");

        specialty.setName(name);
        specialty.setDescription(description);
        specialty.setImageName(imageName);

        specialtyRepository.save(specialty);

        return specialty;
    }

    @Override
    public Specialty getSpecialtyById(Long id) {
        return specialtyRepository.getSpecialtyById(id);
    }

    /*@Override
    public List<Specialty> findByNameIn(Collection<String> specialtyList) {
        return specialtyRepository.findByNameExistsIn(specialtyList);
    }*/
}
