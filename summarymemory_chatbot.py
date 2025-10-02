from dotenv import load_dotenv
load_dotenv()
from langchain.memory import ConversationSummaryMemory
from langchain_openai import ChatOpenAI

# TODO: Define memory as ConversationSummaryMemory

# Save short conversation turns
memory.save_context(
    inputs={"human": "Which dev method should I use?"},
    outputs={"ai": "Agile for flexibility, Waterfall for fixed plans."},
)
memory.save_context(
    inputs={"human": "Let's go Agile. First step?"},
    outputs={"ai": "Define vision, create backlog."},
)
# Retrieve stored history
history_text = memory.load_memory_variables({})["history"]
print(history_text)

from langchain.chains import ConversationChain
# Attach memory to a ConversationChain
llm = ChatOpenAI(model="gpt-5-nano")
conversation = ConversationChain(
    llm=llm,
    memory=memory,
    verbose=True
)
# Continue conversation using the chain
print(conversation.invoke("Remind me which method I chose."))
print(conversation.invoke("Explain about the method I chose in a line."))
