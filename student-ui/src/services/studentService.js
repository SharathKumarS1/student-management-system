import axios from "axios";

const API_URL =
  "http://localhost:8082/api/students";

const getAuthHeader = () => {

  const token =
    localStorage.getItem("token");

  return {

    Authorization:
      `Bearer ${token}`
  };
};

export const getStudents = () =>
  axios.get(
    API_URL,
    {
      headers:
        getAuthHeader()
    }
  );

export const getStudent = (id) =>
  axios.get(
    `${API_URL}/${id}`,
    {
      headers:
        getAuthHeader()
    }
  );

export const createStudent = (student) =>
  axios.post(
    API_URL,
    student,
    {
      headers:
        getAuthHeader()
    }
  );

export const updateStudent = (
  id,
  student
) =>
  axios.put(
    `${API_URL}/${id}`,
    student,
    {
      headers:
        getAuthHeader()
    }
  );

export const deleteStudent = (id) =>
  axios.delete(
    `${API_URL}/${id}`,
    {
      headers:
        getAuthHeader()
    }
  );