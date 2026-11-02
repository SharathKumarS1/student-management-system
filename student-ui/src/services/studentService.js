import axios from "axios";

// const API_URL =
//   "http://localhost:8082/api/students";
const API_URL =
  "https://st-c0265820e0fb483d8c0b49dd431d82f5.ecs.ap-southeast-2.on.aws/api/students";

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