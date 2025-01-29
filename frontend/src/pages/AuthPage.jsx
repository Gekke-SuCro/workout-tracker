import { Link } from "react-router-dom";
import { capitalizeFirstLetter } from "../utils/textUtils";
import FormInputBox from "../components/form/FormInputBox";

const loginInputs = [
  {
    type: "text",
    name: "username",
    id: "username",
    placeholder: "johndoe",
    required: true,
  },
  {
    type: "password",
    name: "password",
    id: "password",
    placeholder: "",
    required: true,
  },
];

const AuthPage = () => {
  const updateValue = (value) => {
    console.log(value);
  };

  const login = (event) => {
    event.preventDefault();
    const username = document.querySelector("#username").value;
    const password = document.querySelector("#password").value;
    console.log(`Login requested username: ${username} password: ${password}`);
  };

  return (
    <div className="mx-auto sm:max-w-md flex flex-col grow justify-center min-h-svh">
      <form onSubmit={login} className="p-8 md:shadow-xl md:p-16">
        <h1 className="title mb-4">Login</h1>

        <p>
          Not a member?{" "}
          <span className="text-blue-500">
            <Link>Register</Link>
          </span>
        </p>

        <div className="flex flex-col gap-4 py-8">
          {loginInputs.map((item, index) => (
            <div key={index} className="flex flex-col gap-2">
              <label htmlFor={item.name} className="text-neutral-600">
                {capitalizeFirstLetter(item.name)}
              </label>
              <FormInputBox type={item.type} name={item.name} id={item.id} />
            </div>
          ))}
        </div>

        <button
          type="submit"
          className="mt-4 bg-blue-500 text-neutral-50 px-4 py-2 rounded-lg"
        >
          Login
        </button>
      </form>
    </div>
  );
};

export default AuthPage;
