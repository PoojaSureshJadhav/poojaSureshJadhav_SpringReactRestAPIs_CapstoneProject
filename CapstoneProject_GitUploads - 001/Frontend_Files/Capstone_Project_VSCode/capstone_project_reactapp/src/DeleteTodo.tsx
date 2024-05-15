import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

type RouteParams = {
  id: string;
};

interface Todo {
  todoId: number;
  todoTitle: string;
  todoDescription: string;
  todoCompleted: boolean;
}

const TodoDelete: React.FC = () => {
  const { id } = useParams<RouteParams>();
  const [todo, setTodo] = useState<Todo>();

  const fetchData = async () => {
    try {
      const response = await axios.get<Todo>(
        `http://localhost:8085/api/todos/${id}`
      );
      setTodo(response.data);
    } catch (error) {
      console.error("Error fetching todo:", error);
    }
  };

  const deleteTodo = async () => {
    try {
      await axios.delete(`http://localhost:8085/api/todos/${id}`);
      alert("Todo activity deleted successfully");
    } catch (error) {
      console.error("Error deleting todo:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <h1>Delete Todo</h1>
      {todo && (
        <div>
          <p>Todo ID: {todo.todoId}</p>
          <p>Title: {todo.todoTitle}</p>
          <p>Description: {todo.todoDescription}</p>
          <button onClick={deleteTodo}>Delete Todo</button>
        </div>
      )}
    </div>
  );
};

export default TodoDelete;
