import axios from "axios";
import { API_BASE_URL, KAKAO_API_URL } from "@/config";

//axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function kakaoInstance() {
  const instance = axios.create({
    baseURL: KAKAO_API_URL,
    headers: {
      Authorization: "KakaoAK b7e5dd660d54f006d12923bcee442da5",
    },
  });
  return instance;
}

export { apiInstance, kakaoInstance };
