const timestamp = Date.now();

const studentData = {

    name: `Sharath_${timestamp}`,

    email: `sharath${timestamp}@gmail.com`,

    department: 'CSE',

    phoneNumber: '9876543210'
};

const updatedStudentData = {

    name: `SharathKumar_${timestamp}`,

    email: `sharath${timestamp}@gmail.com`,

    department: 'ECE',

    phoneNumber: '9876597569'
};

module.exports = { studentData, updatedStudentData};