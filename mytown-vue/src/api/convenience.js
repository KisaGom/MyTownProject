import { kakaoInstance } from ".";

const kakao = kakaoInstance();

function searchCategory(params, success, fail) {
  kakao.get(``, { params: params }).then(success).catch(fail);
}

export { searchCategory };
