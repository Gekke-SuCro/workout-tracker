import * as PropTypes from "prop-types";

const AuthInput = ({label, id, type, register, validation, error, placeholder}) => {
    return (
        <div>
            <label htmlFor={id} className="form-label">{label}</label>
            <input
                {...register(id, validation)}
                type={type}
                id={id}
                placeholder={placeholder}
                className={`form-input-box ${error && "error"}`}
            />
            {error && <p className="form-error-label">{error.message}</p>}
        </div>
    );
}

AuthInput.propTypes = {
    label: PropTypes.string.isRequired,
    id: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    register: PropTypes.any.isRequired,
    validation: PropTypes.any.isRequired,
    error: PropTypes.object,
    placeholder: PropTypes.string.isRequired,
}

export default AuthInput;
