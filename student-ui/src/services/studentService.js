import axios from "axios";

// const API_URL =
//   "http://localhost:8082/api/students";

// const API_URL =
// "http://a5da0e4509aa3466085a726179623833-1129893059.ap-southeast-2.elb.amazonaws.com/api/students";

const API_URL = "/api/students";

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