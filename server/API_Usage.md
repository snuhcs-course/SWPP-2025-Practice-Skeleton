# API Usage Guide

This document provides examples of how to use the Challengers API.

---

## 1. Fetching All Todos

Fetches a list of all "To-Do" items.

- **Method**: `GET`
- **URL**: `/snapdo/todos/`

### Example Request

```bash
curl -X GET http://127.0.0.1:8000/snapdo/todos/
```

### Example Response

```json
[
    {
        "id": 1,
        "title": "My First Task",
        "description": "Details about the task.",
        "target_evidence": "A photo of a clean desk",
        "is_done": false,
        "created_at": "2023-10-27T10:00:00Z",
        "owner": "default_user"
    }
]
```

---

## 2. Adding a Todo Task

Creates a new "To-Do" task. The `title` field is required.

- **Method**: `POST`
- **URL**: `/snapdo/todos/`

### Example Request

```bash
curl -X POST http://127.0.0.1:8000/snapdo/todos/ \
-H "Content-Type: application/json" \
-d '{
    "title": "Clean the kitchen",
    "description": "Wipe the counters and do the dishes.",
    "target_evidence": "A photo of a sparkling clean kitchen"
}'
```

### Example Response

```json
{
    "id": 2,
    "title": "Clean the kitchen",
    "description": "Wipe the counters and do the dishes.",
    "target_evidence": "A photo of a sparkling clean kitchen",
    "is_done": false,
    "created_at": "2023-10-27T10:05:00Z",
    "owner": "default_user"
}
```

---

## 3. Deleting a Todo Task

Deletes a specific "To-Do" task by its ID.

- **Method**: `DELETE`
- **URL**: `/snapdo/todos/<task_id>/`

### Example Request

Replace `<task_id>` with the ID of the task you want to delete.

```bash
curl -X DELETE http://127.0.0.1:8000/snapdo/todos/2/
```

### Example Response

A successful deletion will return a `204 No Content` status and no body.

---

## 4. Generate Evidence for a Task

Asks the AI for a suggestion on how to prove a task is complete, based on a title. This does not require the task to exist in the database.

- **Method**: `POST`
- **URL**: `/snapdo/generate_evidence/`

### Example Request

```bash
curl -X POST http://127.0.0.1:8000/snapdo/generate_evidence/ \
-H "Content-Type: application/json" \
-d '{
    "title": "Read 30 pages of a book",
    "description": "A photo of the book on page 31"
}'
```

### Example Response

```json
{
    "evidence": "Place today’s dated sticky note beside page 31 and photograph both in one frame.",
    "task_title": "Read 30 pages of a book"
}
```

---

## 5. Verify a Task with an Image

Submits a base64-encoded image as evidence to verify that a task is complete.

- **Method**: `POST`
- **URL**: `/snapdo/todos/<task_id>/verify/`

### Example Request

Replace `<task_id>` with the ID of the task and `<your_base64_image_string>` with the actual base64 data of your image.

```bash
curl -X POST http://127.0.0.1:8000/snapdo/todos/1/verify/ \
-H "Content-Type: application/json" \
-d '{
    "image_base64": "data:image/jpeg;base64,<your_base64_image_string>"
}'
```

### Example Response

```json
{
    "verdict": "PASSED",
    "score": 0.95,
    "reason": "The image clearly shows the book open to the correct page next to a dated note.",
    "evidence_id": 3,
    "timestamp": "2023-10-27T11:30:00Z"
}
```
