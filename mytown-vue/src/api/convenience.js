import { kakaoInstance } from ".";

const kakao = kakaoInstance();

function searchCategory(params, success, fail) {
  kakao.get(`/category.json?`, { params: params }).then(success).catch(fail);
}

export { searchCategory };
