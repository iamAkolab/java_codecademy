# Week 1: API & Interface Design

## 🎯 Goal
Design versioned, backward-compatible APIs using both REST and gRPC.

## 📚 Resources
- [Google API Design Guide](https://cloud.google.com/apis/design)
- gRPC Best Practices (gRPC.io)

## ✅ Tasks
1. Build a REST API with `/v1/trades` and `/v2/trades` (add new field without breaking v1)
2. Define a `.proto` file for TradeService with gRPC methods
3. Implement server + client in Go or Python
4. Document contract evolution strategy

## 🧪 Challenge
> Deprecate a field in v2 without breaking existing clients. Prove backward compatibility with automated tests.

## 📁 Labs
- `labs/rest-api/`
- `labs/grpc-service/`

➡️ **Next**: Integrate this API into `capstone-project/api/`
