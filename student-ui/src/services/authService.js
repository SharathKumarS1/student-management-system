import axios from "axios";

// const API_URL = "http://localhost:8082/api/auth";

const API_URL =
"http://a5da0e4509aa3466085a726179623833-1129893059.ap-southeast-2.elb.amazonaws.com/api/auth";

export const registerUser = async (userData) => {
    const response = await axios.post(
        `${API_URL}/register`,
        userData
    );

    return response.data;
};

export const loginUser = async (loginData) => {
    const response = await axios.post(
        `${API_URL}/login`,
        loginData
    );

    return response.data;
};
