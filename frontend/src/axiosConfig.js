import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://127.0.0.1:8080/api',
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  },
});

export default axiosInstance;
