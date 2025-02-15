import {set, useForm} from "react-hook-form";
import CreateWorkoutFormLayout from "../components/forms/CreateWorkoutFormLayout.jsx";
import ReactHookInput from "../components/forms/input/ReactHookInput.jsx";
import AuthSubmitButton from "../components/forms/AuthFormSubmitButton.jsx";
import {useEffect, useState} from "react";
import {WorkoutAPI} from "../../api/WorkoutAPI.js";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";
import {ExerciseAPI} from "../../api/ExerciseAPI.js";
import SelectExercise from "../components/dynamic-component/SelectExercise.jsx";

const LogWorkoutPage = () => {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm({mode: "onBlur"});

    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const [exercises, setExercises] = useState([]);
    const [workoutExercises, setWorkoutExercises] = useState([]);

    useEffect(() => {
        const loadAllExercises = async () => {
            try {
                const exercisesData = await ExerciseAPI.getAll();
                setExercises(exercisesData);
            } catch (error) {
                console.error(error);
            }
        }

        loadAllExercises();
    }, []);

    const addToWorkout = (exercise) => {
        const workoutRequestBody = {
            name: exercise.name,
            sets: [],
        }
        setWorkoutExercises((prev) => {
            if (!prev.find((ex) => ex.name === exercise.name)) {
                return [...prev, workoutRequestBody];
            }
            return prev;
        })
    }

    const removeFromWorkout = (exercise) => {
        setWorkoutExercises((prev) => prev.filter(ex => ex.name !== exercise.name));
    }


    const onSubmit = async (data) => {
        setLoading(true);
        const createWorkoutToast = toast.loading("Logging workout...");
        try {
            await WorkoutAPI.create(data.workoutName, data.workoutDate, workoutExercises);
            toast.update(createWorkoutToast, {
                render: `'${data.workoutName}' workout logged`,
                type: "success",
                isLoading: false,
                autoClose: 3000
            });
            navigate("/");
        } catch (error) {
            toast.update(createWorkoutToast, {render: error.message, type: "error", isLoading: false, autoClose: 3000});
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <CreateWorkoutFormLayout onSubmit={handleSubmit(onSubmit)} title="Log Workout">
                <div className="flex flex-col gap-4 py-8">
                    <ReactHookInput label="Workout Name" id="workoutName" type="text" register={register}
                                    validation={{required: "Workout name is required"}} error={errors.workoutName}
                                    placeholder="Leg Day Workout"/>

                    <ReactHookInput label="Workout Date" id="workoutDate" type="date" register={register}
                                    validation={{required: "Workout date is required"}} error={errors.workoutDate}
                                    placeholder="Leg Day Workout"
                                    defaultValue={new Date().toISOString().split("T")[0]}/>
                </div>

                {/*  Dynamic Component */}
                <div className="p-8 border-2 border-neutral-500 rounded">
                    <SelectExercise exercises={exercises} oncClick={addToWorkout}/>
                </div>

                {/* Exercises list */}
                <div className="py-8 border-2 border-neutral-500 rounded">
                    <div className="flex flex-col gap-2 py-4 overflow-y-auto max-h-[256px] p-8">
                        {workoutExercises.map((ex, index) => (
                            <div key={index} className="flex justify-between border-2 border-neutral-500 rounded p-2">
                                <div>{ex.name}</div>
                                <div>
                                    <button type="button" onClick={() => removeFromWorkout(ex)}>remove</button>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                <div className="flex justify-center gap-4">
                    <AuthSubmitButton loading={loading} text="Log Workout"/>
                    <button onClick={() => navigate("/")}
                            className="mt-4 px-6 py-2 rounded-lg bg-red-500 rounded-xl text-white">Cancel
                    </button>
                </div>
            </CreateWorkoutFormLayout>
        </div>
    );
};

export default LogWorkoutPage;
