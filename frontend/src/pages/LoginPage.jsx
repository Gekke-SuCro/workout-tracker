import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import "../assets/styles/formStyles.css";

const LoginPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: "onBlur" });

  const onSubmit = () => {
    console.log("Logging in");
  };

  return (
    <div className="mx-auto sm:max-w-md flex flex-col grow justify-center min-h-svh">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="p-8 md:shadow-xl md:p-16 rounded-xl"
      >
        <h1 className="title mb-4">Login</h1>

        <p>
          Not a member?{" "}
          <span className="text-blue-500">
            <Link to="/">Register</Link>
          </span>
        </p>

        <div className="flex flex-col gap-4 py-8">
          {/* Username Input */}
          <div>
            <input
              {...register("username", {
                required: "Username is required",
              })}
              type="text"
              id="username"
              placeholder="johndoe1"
              className={`form-input-box ${errors.username && "error"}`}
            />
            {errors.username && (
              <p className="form-error-label">{errors.username.message}</p>
            )}
          </div>

          {/* Password Input */}
          <div>
            <input
              {...register("password", {
                required: "Password is required",
              })}
              type="password"
              id="password"
              placeholder="********"
              className={`form-input-box ${errors.password && "error"}`}
            />
            {errors.password && (
              <p className="form-error-label">{errors.password.message}</p>
            )}
          </div>
        </div>

        {/* Submit Button */}
        <div className="flex justify-center md:justify-start">
          <button
            type="submit"
            className={`mt-4 px-6 py-2 rounded-lg transition bg-blue-500 text-white hover:bg-blue-600`}
          >
            Login
          </button>
        </div>
      </form>
    </div>
  );
};

export default LoginPage;
