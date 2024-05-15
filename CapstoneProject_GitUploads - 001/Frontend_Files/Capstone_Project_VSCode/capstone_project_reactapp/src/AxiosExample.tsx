import React, { useEffect, useState } from "react";
import axios from "axios";
import "./styles.css";
import AddTodoForm from "./AddTodo";

interface Todo {
  todoId: number;
  todoTitle: string;
  todoDescription: string;
  todoCompleted: boolean;
}

const AxiosExample: React.FC = () => {
  const [posts, setPosts] = useState<Todo[]>([]);
  const [showAddForm, setShowAddForm] = useState(false);            // State for form visibility
  const [editMode, setEditMode] = useState(false);                  // State for edit mode
  const [editedTodo, setEditedTodo] = useState<Todo | null>(null);  // State for the todo being edited

  const fetchData = async () => {
    const response = await axios.get<Todo[]>("http://localhost:8085/api/todos");
    console.log(response.data);
    setPosts(response.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const complete = async (id: number, title: string, description: string) => {
    await axios.put(`http://localhost:8085/api/todos/${id}`, {
      todoCompleted: true,
      todoTitle: title,
      todoDescription: description,
    });
    fetchData();
  };

  const inComplete = async (id: number, title: string, description: string) => {
    await axios.put(`http://localhost:8085/api/todos/${id}`, {
      todoCompleted: false,
      todoTitle: title,
      todoDescription: description,
    });
    fetchData();
  };

  const addTodo = async (title: string, description: string) => {
    try {
      const response = await axios.post<Todo>("http://localhost:8085/api/todos", {
        todoTitle: title,
        todoDescription: description,
        todoCompleted: false,
      });
  
      const newTodo: Todo = response.data;
      setPosts([...posts, newTodo]);                // Add the new todo to the list
      setShowAddForm(false);                        // Hide the form after adding todo
    } catch (error) {
      console.error("Error adding todo:", error);
      // Handle error, such as showing an error message to the user
    }
  };

  const startEditing = (todo: Todo) => {
    setEditMode(true);
    setEditedTodo(todo);
  };

  const cancelEditing = () => {
    setEditMode(false);
    setEditedTodo(null);
  };

  const saveChanges = async () => {
    if (editedTodo) {
      try {
        await axios.put(`http://localhost:8085/api/todos/${editedTodo.todoId}`, {
          todoTitle: editedTodo.todoTitle,
          todoDescription: editedTodo.todoDescription,
          todoCompleted: editedTodo.todoCompleted,
        });
        fetchData();                                // Refresh the data after update
        setEditMode(false);
        setEditedTodo(null);
      } catch (error) {
        console.error("Error updating todo:", error);
        // Handle error, such as showing an error message to the user
      }
    }
  };

  const deleteTodo = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8085/api/todos/${id}`);
      fetchData();                                  // Refresh the data after deletion
    } catch (error) {
      console.error("Error deleting todo:", error);
      // Handle error, such as showing an error message to the user
    }
  };

  return (
    <div className="table-container">
      <h1 className="header">Todo Management Application</h1>
      <h1 className="heading">List of Todos</h1>
      <br />
      <button className="add-button" type="button" onClick={() => setShowAddForm(true)}>Add Todo</button>

      {/* Conditionally render AddTodoForm based on showAddForm state */}
      {showAddForm && <AddTodoForm addTodo={addTodo}></AddTodoForm>}

      <table className="table1-container">
        <thead>
          <tr>
            <th>Todo Title</th>
            <th>Todo Description</th>
            <th>Todo Completed</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {posts.map((temp) => (
            <tr key={temp.todoId}>
              <td>{editMode && editedTodo?.todoId === temp.todoId ? (
                <input
                  type="text"
                  value={editedTodo.todoTitle}
                  onChange={(e) => setEditedTodo({ ...editedTodo, todoTitle: e.target.value })}
                />
              ) : temp.todoTitle}</td>
              <td>{editMode && editedTodo?.todoId === temp.todoId ? (
                <input
                  type="text"
                  value={editedTodo.todoDescription}
                  onChange={(e) => setEditedTodo({ ...editedTodo, todoDescription: e.target.value })}
                />
              ) : temp.todoDescription}</td>
              <td>{temp.todoCompleted ? "YES" : "NO"}</td>
              <td>
                {editMode && editedTodo?.todoId === temp.todoId ? (
                  <>
                    <button className="save-button" onClick={saveChanges}>Save</button>
                    <button className="cancel-button" onClick={cancelEditing}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button className="update-button" onClick={() => startEditing(temp)}>Update</button>
                    <button className="delete-button" onClick={() => deleteTodo(temp.todoId)}>Delete</button>
                    <button className="complete-button" onClick={() => complete(temp.todoId, temp.todoTitle, temp.todoDescription)}>Complete</button>
                    <button className="incomplete-button" onClick={() => inComplete(temp.todoId, temp.todoTitle, temp.todoDescription)}>In Complete</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <footer>
        <p>©Copyrights Reserved</p>
      </footer>
    </div>
  );
};

export default AxiosExample;
