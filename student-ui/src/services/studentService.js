import axios from "axios";

// const API_URL =
//   "http://localhost:8082/api/students";
const API_URL =
  "http://abe7cbf08d9814835bef24abdeab1af0-346636190.ap-southeast-2.elb.amazonaws.com:8082/api/students";

export const getStudents = () =>
  axios.get(API_URL);

export const getStudent = (id) =>
  axios.get(`${API_URL}/${id}`);

export const createStudent = (student) =>
  axios.post(API_URL, student);

export const updateStudent = (id, student) =>
  axios.put(`${API_URL}/${id}`, student);

export const deleteStudent = (id) =>
  axios.delete(`${API_URL}/${id}`);