# baseball
> 숫자 야구 게임 API 구현


## 제한 사항
- 답변은 최대 10번까지만 할 수 있다
- 답변 횟수가 최대치를 넘거나, 정답을 맞추는 경우 게임이 종료된 걸로 한다
- `room id`가 발급되고, 중복되지 않는 1-9 사이의 세 숫자가 정답으로 저장이 되어있어야 함

## 구현 API
### 게임 시작 (POST, `/game/start`)
response
```
{
    "success": true,
    "data": {
    "roomId": 123
    }
}
```

<br>

### 게임 진행 (POST` /game/123/answer`)
request
```
{    
    "answer": "345"
}
```
response(게임 종료가 아닐 시)
```
{
    "success": true,
    "data": {
    "correct": true, // false
    "remainingCount": 8,
    "strike": 3,
    "ball": 0,
    "out": 0
    }
}
```
resopnse(게임 종료 시)
```

{
    "success": false,
    "data": null,
    "error": {
    "code": "CLOSED_GAME",
    "message": ""
    }
}
```

<br>

### 게임 결과 (GET `/game/123`)
response
```
{
    "success": true,
    "data": {
    "remainingCount": 8,
    "answerCount": 2
    }
}
```

<br>

### 게임 진행 기록 (GET `/game/123/history`)
response
```
{    
    "success": true,
    "data": {
        histories: [
            {
                "answer": "123",
                "result": {
                    "strike": 0,
                    "ball": 0,
                    "out": 3
                }
            },
            {
                "answer": "456",
                "result": {
                    "strike": 0,
                    "ball": 2,
                    "out": 1
                }
            }
        ]
    }
}
```