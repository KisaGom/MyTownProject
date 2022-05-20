import axios from "axios";
import { API_BASE_URL } from "@/config";

//axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      contentType: "application/json",
    },
  });
  return instance;
}

export { apiInstance };
