export const useUsername = () =>
  useState<string>("username", () => useCookie("username").value);
export const usePassword = () =>
  useState<string>("password", () => useCookie("password").value);
export const useRole = () =>
  useState<string>("role", () => useCookie("role").value);

// export const useMembers = () =>
//   useState<unknown[]>("members", () => {
//     const base64auth = btoa(`${useUsername().value}:${usePassword().value}`);
//     useAsyncData("students", () =>
//       $fetch("http://localhost:8080/api/users/", {
//         headers: {
//           Authorization: `Basic ${base64auth}`,
//         },
//       })
//     ).then((x) => {
//       return x;
//     });
//     return undefined;
//   });

export const useMembers = () => useState<any[]>("members");

export const changeUsername = (username: string) => {
  useCookie("username").value = username;
  useUsername().value = username;
};

export const changePassword = (password: string) => {
  useCookie("password").value = password;
  usePassword().value = password;
};

export const changeRole = (role: string) => {
  useCookie("role").value = role;
  useRole().value = role;
};
