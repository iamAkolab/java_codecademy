# Architecture Decisions Record (ADR)

Log key technical choices made during this learning journey.

## Format
- **Decision**: What was decided?
- **Context**: Why was this considered?
- **Consequences**: Pros, cons, trade-offs

---

### Example Entry

#### Decision: Use gRPC for internal service communication  
**Context**: Need strong contracts, low latency, and code generation for trading agent components.  
**Consequences**:  
✅ Strong typing, efficient serialization  
⚠️ Requires protobuf knowledge; less human-readable than REST  
🔁 Backward compatibility enforced via versioned `.proto` files
