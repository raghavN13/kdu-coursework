import { ApiQuote } from "../types/quotes.types"
import './Quote.scss'
interface QuotesProps {
    quote: ApiQuote
    handleClickTag : (tag:string) => void; 
}

export function Quote({ quote,handleClickTag }: QuotesProps) {
    return (
        <div className="quote-container">
        <p id="content">{quote.content}</p>
        <p id="author">- {quote.author}</p>
        <p id="date">{quote.dateAdded}</p>
        <div className="tag-buttons">
          {quote.tags.map((tag, index) => (
            <button key={tag} onClick={()=>handleClickTag(tag)}>{tag}</button>
          ))}
        </div>
      </div>
    )
}