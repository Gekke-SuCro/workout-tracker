import { useAuth } from "../../context/authContext";

const HomePage = () => {
  const { logout } = useAuth();

  return (
    <div className="flex flex-col grow justify-center items-center min-h-screen">
      <h1>Workout Tracker</h1>

      <div className="py-8">
        <button onClick={logout} className="px-6 py-2 bg-red-500 rounded-xl">
          Logout
        </button>
      </div>
    </div>
  );
};

export default HomePage;
