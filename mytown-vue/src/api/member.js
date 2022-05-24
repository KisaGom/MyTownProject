import { apiInstance } from "./index.js";

const api = apiInstance();

async function listUser(success, fail) {
  await api.get(`/user/`).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function register(user, success, fail) {
  await api
    .post(`/user/register`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function modify(user, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/user/modify`, JSON.stringify(user)).then(success).catch(fail);
}

async function deleteUser(userid, success, fail) {
  await api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

async function findPwd(user, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .post(`/user/findPwd`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

// function logout(success, fail)

export { login, findById, register, modify, deleteUser, findPwd, listUser };
