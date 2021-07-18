package com.kafkamongo.kafkamongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	KafkaTemplate<String, String> template;

	public String topic = "KafkaSpring";

	@GetMapping(value = "/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hi" + name + "welcome to kafka");
		return "Data published";
	}

	@GetMapping(value = "/publishJSON")
	public String publishMessage() {
		Student stud = new Student(12, "rutu", 33, 11);
		Gson gson = new Gson();
		String json = gson.toJson(stud);
		System.out.println(json);
		template.send(topic, json);
		return "Data published";
	}


	@GetMapping(value = "/all")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@PostMapping("/create")
	public String createStudent(@RequestBody Student student) {
		Student insert = studentRepository.insert(student);
		System.out.println(insert.toString());
		return "student created" + insert.getName();
	}

	@GetMapping(value = "/student/{id}")
	public ResponseEntity<Student> getSTudentById(@PathVariable(value = "id") Long studentId) {
		Student stud = studentRepository.findById(studentId).orElseThrow();
		return ResponseEntity.ok(stud);
	}

}
